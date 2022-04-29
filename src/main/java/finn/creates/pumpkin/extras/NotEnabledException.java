package finn.creates.pumpkin.extras;

import org.apache.commons.lang.NotImplementedException;

public class NotEnabledException extends NotImplementedException {
    public NotEnabledException(String errorMessage) {
        super(errorMessage);
    }
}
