package shop.model;

import java.util.Objects;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:23
 */
public class Customer {
    private final String id;
    private final String name;
    private final boolean vip;
    private final boolean student;
    private final int membershipMonths;

    public Customer(String id, String name, boolean vip,
                    boolean student, int membershipMonths) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.vip = vip;
        this.student = student;
        if (membershipMonths < 0) {
            throw new IllegalArgumentException("Membership months cannot be negative");
        }
        this.membershipMonths = membershipMonths;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isVip() { return vip; }
    public boolean isStudent() { return student; }
    public int getMembershipMonths() { return membershipMonths; }

    @Override
    public String toString() {
        return String.format("Customer[%s, %s, vip=%s, student=%s, months=%d]",
                id, name, vip, student, membershipMonths);
    }
}
