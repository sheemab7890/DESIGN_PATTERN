package CREATIONAL;

/*
 * Builder Design Pattern.
 *
 * This pattern is used when an object has many optional fields
 * and we want a clean and readable way to create that object.
 */
public class Builder_Design_Pattern {

    public static void main(String[] args) {

        // Creating a single builder instance
        User.UserBuilder builder = new User.UserBuilder();

        /*
         * Build first User object using the builder.
         * Method chaining is possible because each builder method
         * returns the same builder instance (return this).
         */
        User user1 = builder
                .name("Sheemab")
                .email("Sheemab@gmail.com")
                .build();

        System.out.println(user1.toString());

        /*
         * Reusing the same builder to build another User object.
         * (Note: builder keeps previous values unless changed)
         */
        User user2 = builder
                .name("Azeez")
                .email("Sheemab@gmail.com")
                .phone("7906090382")
                .build();

        System.out.println(user2.toString());
    }
}


class User {

    // Actual object fields
    private String name;
    private String email;
    private String phone;
    private String address;

    /*
     * Private constructor.
     * User objects can be created only using UserBuilder.
     */
    private User(UserBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }


    /*
     * ----------------------------
     * Why is UserBuilder nested inside User?
     * ----------------------------
     *
     * 1. Encapsulation:
     *    UserBuilder is only meant to build User objects.
     *    Keeping it inside User clearly shows that relationship.
     *
     * 2. Access to private data:
     *    Being inside the same outer class, the builder can
     *    easily pass values to User's private constructor
     *
     * ----------------------------
     * Why is UserBuilder static?
     * ----------------------------
     *
     * 1. No need for User instance:
     *    We should be able to create a UserBuilder without
     *    first creating a User object.
     */
    static class UserBuilder {

        // Temporary fields used while building the User object
        private String name;
        private String email;
        private String phone;
        private String address;

        /*
         * Sets the name for the User being built.
         *
         * Why return this?
         * ----------------
         * Returning the same builder object allows
         * method chaining (fluent API).
         */
        public UserBuilder name(String name) {
            this.name = name;
            return this;   // return the same builder instance
        }

        /*
         * Sets the email and returns the same builder
         * to continue building.
         */
        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        /*
         * Sets the phone and returns the same builder
         * for method chaining.
         */
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        /*
         * Sets the address and returns the same builder
         * for chaining.
         */
        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        /*
         * Final step of the builder.
         * Creates and returns the actual User object
         * using the private User constructor.
         */
        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}



