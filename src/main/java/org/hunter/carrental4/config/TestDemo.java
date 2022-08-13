package org.hunter.carrental4.config;

public class TestDemo {

//    @Configuration
//    @Import(MovieRepository.class)
//    class Config {
//        @Bean
//        public DriverManagerDataSource dataSource() {
//            DriverManagerDataSource ds = new DriverManagerDataSource();
//            ds.setDriverClassName("org.h2.Driver");
//            ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
//            return ds;
//        }
//        @Bean
//        public JdbcTemplate jdbcTemplate(DriverManagerDataSource ds) {
//            return new JdbcTemplate(ds);
//        }
//    }
//
//    class Movie {
//        public String name;
//        public int year;
//        public int rating;
//
//        public Movie(){}
//
//        public Movie(String name, int year, int rating) {
//            this.name = name;
//            this.year = year;
//            this.rating = rating;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getYear() {
//            return year;
//        }
//
//        public void setYear(int year) {
//            this.year = year;
//        }
//
//        public int getRating() {
//            return rating;
//        }
//
//        public void setRating(int rating) {
//            this.rating = rating;
//        }
//    }
//
//    @Repository
//    public class MovieRepository {
//
//        @Autowired
//        private JdbcTemplate template;
//
//        @PostConstruct
//        public void createTable() {
//            template.execute("CREATE TABLE movies (id bigint auto_increment primary key, name VARCHAR(50), year int, " +
//                    "rating int)");
//        }
//
//        public void createMovie(String name, int year, int rating) {
//            template.update("INSERT INTO movies (id, name, year, rating) VALUES (?,?,?,?)",
//                    null, name, year, rating);
//        }
//
//        public List<Movie> findMoviesByName(String nameStartsWith) {
//            String sql = "select name, year, rating from movies where 1=1";
//
//            if (nameStartsWith != null && nameStartsWith.length() > 0) {
//                sql += " and name like ?";
//            }
//
//            List<Movie> query = template.query(sql, new BeanPropertyRowMapper<Movie>(Movie.class), nameStartsWith + "%");
//
//            return query;
//        }
//    }


}
