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
        String qlQuerry = """
                SELECT r
                FROM Reservation r
                WHERE ?1 BETWEEN r.begin.getYear() AND r.end.getYear()
                    AND ?2 BETWEEN r.begin.getMonth() AND r.end.getMonth()
                """;
        TypedQuery<Reservation> query = em.createQuery(qlQuerry, Reservation.class);
        query.setParameter(1, year);
        query.setParameter(2, month.getValue());

        Set<Reservation> result = query.getResultStream().collect(Collectors.toSet());
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
                        criteriaBuilder.lessThan(root.get("begin"), date),
                        criteriaBuilder.greaterThan(root.get("end"), date)
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
                .where(criteriaBuilder.greaterThan(root.get("begin"), LocalDate.now()));

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
