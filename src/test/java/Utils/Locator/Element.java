package Utils.Locator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Element {
    private String name;
    private List<Locator> locators;
}
