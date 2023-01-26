package be.technifutur.mvc.repositories.impl;

import be.technifutur.mvc.models.entities.Reservation;
import be.technifutur.mvc.utils.EMFSharer;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.time.*;
import java.util.Set;
import java.util.stream.Collectors;

public class ReservationRepoImpl extends AbstractCrudRepoImpl<Reservation, Long> implements be.technifutur.mvc.repository.ReservationRepository {

    public ReservationRepoImpl() {
        super(Reservation.class, EMFSharer.getInstance().createEntityManager());
    }

    @Override
    public Set<Reservation> getFromMonth(Month month, int year) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);

        boolean isLeapYear = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
        LocalDateTime dateD = LocalDateTime.of(year,month,1,0,0,0);
        LocalDateTime dateF = LocalDateTime.of(year,month,month.length(isLeapYear),23,59,59,999_999_999);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.and(
                                criteriaBuilder.lessThanOrEqualTo(root.get("begin"), dateF),
                                criteriaBuilder.greaterThanOrEqualTo(root.get("end"), dateD)
                        )
                );

        Set<Reservation> result = em.createQuery(criteriaQuery).getResultStream().collect(Collectors.toSet());
        em.clear();

        return result;
    }

    @Override
    public int getBreakfastNeededForDay(LocalDate date) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);

        criteriaQuery
                .select(criteriaBuilder.count(root))
                .where(criteriaBuilder.and(
                            criteriaBuilder.isTrue(root.get("breakfast")),
                            criteriaBuilder.and(
                                    criteriaBuilder.lessThan(root.get("begin"), date),
                                    criteriaBuilder.greaterThan(root.get("end"), date)
                            )
                ));

        return em.createQuery(criteriaQuery).getSingleResult().intValue();
    }

    @Override
    public Set<Reservation> getFutureReservation() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.greaterThan(root.get("begin"), LocalDateTime.now()));

        Set<Reservation> result = em.createQuery(criteriaQuery).getResultStream().collect(Collectors.toSet());
        em.clear();
        return result;
    }

    @Override
    public Set<Reservation> getFutureShortReservation() {
        Set<Reservation> result = getFutureReservation();
        result.forEach(r -> {
            if(Duration.between(r.getBegin(), r.getEnd()).toDays() > 7)
                result.remove(r);
            }
        );

        return result;
    }

    @Override
    public Set<Reservation> getFutureLongReservation() {
        return null;
    }


}
