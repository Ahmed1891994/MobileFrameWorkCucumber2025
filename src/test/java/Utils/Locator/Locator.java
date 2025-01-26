package Utils.Locator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Locator {
    Strategy strategy;
    String value;
}
