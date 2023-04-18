package validators;

import interfaces.Validator;
import pojos.Machine;
import pojos.Person;
import services.PaymentFinder;

public class PaymentValidator implements Validator {

    public PaymentValidator() {
    }

    @Override
    public boolean validate(Person person, Machine machine) {
        PaymentFinder paymentFinder = new PaymentFinder();
        return paymentFinder.findPayment(person);
    }
}
