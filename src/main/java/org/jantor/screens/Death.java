package org.jantor.screens;

import org.jantor.constants.Constants;
import org.jantor.ui.Button;
import org.jantor.ui.Text;

public class Death extends Screen {
    Button reset = new Button("Reset", Button.ButtonType.LEVEL, "one");
    Text deathMessage = new Text();

    public Death(String deathReason) {
        deathMessage.setText(deathReason);

        deathMessage.addToMiddle(this);
        addObject(reset, Constants.screenSize.x / 2, Constants.screenSize.y / 2 +200);
    }

}
