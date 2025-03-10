
import dao.*;
import entity.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.SessionCreator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
public class Main {
    private ActorDAO actorDAO;
    private AddressDAO addressDAO;
    private CategoryDAO categoryDAO;
    private CityDAO cityDAO;
    private CountryDAO countryDAO;
    private CustomerDAO customerDAO;
    private FilmDAO filmDAO;
    private FilmTextDAO filmTextDAO;
    private InventoryDAO inventoryDAO;
    private LanguageDAO languageDAO;
    private PaymentDAO paymentDAO;
    private RentalDAO rentalDAO;
    private StaffDAO staffDAO;
    private StoreDAO storeDAO;
    private Session session;

    public static void main(String[] args) {
        Main main = new Main();
        SessionCreator sessionCreator = new SessionCreator();
        try (sessionCreator) {
            main.init(sessionCreator);
            Customer customer = main.createCustomer();
            main.returnFilm();
            main.newRental(customer);
            main.createNewFilmForRent();
        }
    }

    private void init(SessionCreator sessionCreator) {
        session = sessionCreator.getSession();

        actorDAO = new ActorDAO(session);
        addressDAO = new AddressDAO(session);
        categoryDAO = new CategoryDAO(session);
        cityDAO = new CityDAO(session);
        countryDAO = new CountryDAO(session);
        customerDAO = new CustomerDAO(session);
        filmDAO = new FilmDAO(session);
        filmTextDAO = new FilmTextDAO(session);
        inventoryDAO = new InventoryDAO(session);
        languageDAO = new LanguageDAO(session);
        paymentDAO = new PaymentDAO(session);
        rentalDAO = new RentalDAO(session);
        staffDAO = new StaffDAO(session);
        storeDAO = new StoreDAO(session);
    }

    private void createNewFilmForRent() {
        Transaction tx = session.beginTransaction();
        try {
            Set<Category> categories = new HashSet<>();
            categories.add(categoryDAO.getByName("Comedy"));
            categories.add(categoryDAO.getByName("Family"));
            List<Actor> actors = actorDAO.getItems(5, 15);
            Language language = languageDAO.getById((short) 1);

            Film film = new Film();
            film.setCategories(categories);
            film.setTitle("New Film");
            film.setDescription("Description for New Film");
            film.setLanguage(language);
            film.setRentalDuration((byte) 5);
            film.setRentalRate(BigDecimal.valueOf(4.95));
            film.setReplacementCost(BigDecimal.valueOf(7.99));
            film.setActors(new HashSet<>(actors));
            film.setRating(Rating.R);
            film.setSpecialFeatures(Features.TRAILERS);
            filmDAO.save(film);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("An error occurred during creating new film for rent: ", e);
        }
    }

    private void newRental(Customer customer) {
        Transaction tx = session.beginTransaction();
        try {
            Film film = filmDAO.getAvailableFilm();
            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            Store store = storeDAO.getById((short) 1);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Staff staff = store.getStaff().stream().findFirst().orElse(null);
            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setInventory(inventory);
            rental.setCustomer(customer);
            rental.setStaff(staff);
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setPaymentDate(LocalDateTime.now());
            payment.setRental(rental);
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(4.99));
            payment.setStaff(staff);
            paymentDAO.save(payment);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("An error occurred during new rental: ", e);
        }
    }

    private Customer createCustomer() {
        Transaction tx = session.beginTransaction();
        try {
            Store store = storeDAO.getById((short) 1);
            City baku = cityDAO.getByName("Baku");
            Address address = new Address();
            address.setAddress("Zarife Alieva, 22");
            address.setCity(baku);
            address.setDistrict("Sahil");
            address.setPhone("1234567");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setStore(store);
            customer.setFirstName("Michael");
            customer.setLastName("Jackson");
            customer.setAddress(address);
            customer.setIsActive(true);
            customer.setCreateDate(LocalDateTime.now());
            customerDAO.save(customer);

            tx.commit();
            return customer;
        } catch (Exception e) {
            tx.rollback();
            log.error("An error occurred during create new Customer: ", e);
        }
        return null;
    }

    private void returnFilm() {
        Transaction tx = session.beginTransaction();
        try {
            Rental rental = rentalDAO.getUnfinishedRental();
            rental.setReturnDate(LocalDateTime.now());
            System.out.println(rental.getId());
            rentalDAO.update(rental);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("An error occurred during returning Film: ", e);
        }
    }
}
