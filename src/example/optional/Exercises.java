package example.optional;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * تمرین‌های فصل ۱۱ — Optional به‌جای null
 * (بر اساس DTO های PersonCarInsurance.java و EmployeeDepartment.java)
 * <p>
 * هر متد را کامل کنید. قوانین کلی:
 * - در بدنه‌ی متدها از if، حلقه، get() یا isPresent() استفاده نکنید
 * مگر جایی که صراحتاً اجازه داده شده.
 * - همه‌چیز باید با متدهای Optional (map / flatMap / filter / orElse و...) نوشته شود.
 */
public class Exercises {

    // ==============================================================
    // تمرین ۱ — زنجیره‌ی پایه (map / flatMap)
    // ==============================================================
    // هزینه‌ی ماهانه‌ی بیمه‌ی یک شخص را برگردانید.
    // اگر شخص، ماشین یا بیمه‌ای وجود نداشت، مقدار -1.0 برگردانید.
    public double getInsuranceFee(Optional<Person> person) {
        // TODO

        person.flatMap(p -> p.getCar()).flatMap(e -> e.getInsurance()).map(Insurance::getMonthlyFee).orElse(-1.5);
        return 0;
    }


    // ==============================================================
    // تمرین ۲ — filter
    // ==============================================================
    // اسم بیمه را فقط در صورتی برگردانید که:
    //   - سن شخص >= 18 باشد
    //   - هزینه‌ی ماهانه‌ی بیمه‌اش < 100 باشد
    // در غیر این صورت "N/A" برگردانید.
    public String getCheapEligibleInsuranceName(Optional<Person> person) {
        // TODO
        person.filter(p -> p.getAge() >= 18).flatMap(Person::getCar).flatMap(Car::getInsurance).filter(e -> e.getMonthlyFee() < 100).map(Insurance::getName).orElse("N/A");
        return null;
    }


    // ==============================================================
    // تمرین ۳ — ترکیب دو Optional
    // ==============================================================
    // این متد کمکی از قبل وجود دارد (فرض کنید پیاده‌سازی‌اش موجود است):
    public Person lowerRiskPerson(Person p1, Person p2) {
        // پیاده‌سازی واقعی مهم نیست، فقط فرض کنید کار می‌کند

        throw new UnsupportedOperationException("already implemented elsewhere");
    }

    // یک نسخه‌ی null-safe از متد بالا بنویسید:
    // اگر p1 یا p2 خالی باشند، خروجی هم باید Optional خالی باشد.
    // ممنوع: if، isPresent()، get()
    public Optional<Person> lowerRiskPersonSafe(Optional<Person> p1, Optional<Person> p2) {
        // TODO
        return p1.flatMap(a -> p2.map(b -> lowerRiskPerson(a, b)));

//        return Optional.empty();
    }


    // ==============================================================
    // تمرین ۴ — Stream of Optionals
    // ==============================================================
    // مجموعه‌ی اسامیِ متمایزِ مدیرانِ دپارتمان‌هایی که این کارمندان در آن‌ها
    // عضو هستند را برگردانید (فقط دپارتمان‌هایی که مدیر دارند).
    // راهنما: از Optional::stream (جاوا ۹) در flatMap استفاده کنید.
    public Set<String> getDistinctManagerNames(List<Employee> employees) {
        // TODO
        employees.stream().map(Employee::getDepartment)
                .map(o -> o.flatMap(Department::getManager))
                .map(e -> e.map(Employee::getName)).
                flatMap(Optional::stream).collect(Collectors.toSet());
        return null;
    }


    // ==============================================================
    // تمرین ۵ — کار با API قدیمی (exception -> Optional)
    // ==============================================================
    // یک سرویس قدیمی فرضی که یا Insurance برمی‌گرداند یا در صورت نبود
    // یک InsuranceNotFoundException پرتاب می‌کند:
    public Insurance lookupInsurance(String companyCode) throws InsuranceNotFoundException {
        // پیاده‌سازی واقعی مهم نیست، فرض کنید کار می‌کند
        throw new UnsupportedOperationException("already implemented elsewhere");
    }

    // این متد قدیمی را wrap کنید تا Optional<Insurance> برگرداند
    // (شبیه الگوی stringToInt در کتاب).
    public Optional<Insurance> lookupInsuranceSafe(String companyCode) {
        // TODO
        try {
            return Optional.of(lookupInsurance(companyCode));
        } catch (InsuranceNotFoundException e) {
            return Optional.empty();
        }
    }


    // ==============================================================
    // تمرین ۶ — کاربردی و جامع
    // ==============================================================
    // props ممکن است کلید "discountRate" را داشته باشد یا نه.
    // مقدار آن رشته است و باید به double تبدیل شود و باید در بازه‌ی [0, 1] باشد.
    // اگر هرکدام از این شرایط برقرار نبود، 0.0 برگردانید.
    // باید در یک عبارت fluent واحد نوشته شود (بدون if/try صریح در بدنه‌ی این متد).
    public double readDiscountRate(Properties props) {
        // TODO
        Optional.ofNullable(props.getProperty(""))
                .flatMap(Exercises::stringToDouble)
                .filter(rate -> rate >= 0 && rate <= 1)
                .orElse(0.0);
        return 0;
    }
    public static Optional<Double> stringToDouble(String s) {
        try {
            return Optional.of(Double.parseDouble(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }


    // ---- کلاس کمکی برای تمرین ۵ ----
    static class InsuranceNotFoundException extends Exception {
        public InsuranceNotFoundException(String message) {
            super(message);
        }
    }
}
