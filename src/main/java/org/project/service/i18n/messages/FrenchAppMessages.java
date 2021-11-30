package org.project.service.i18n.messages;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.Message;

@Localized("fr")
public interface FrenchAppMessages extends AppMessages {

    @Message("Arménie")
    @Override
    String ARM();

    @Message("Bulgarie")
    @Override
    String BGR();

    @Message("Canada")
    @Override
    String CAN();

    @Message("Belgique")
    @Override
    String BEL();

    @Message("Danemark")
    @Override
    String DNK();
}
