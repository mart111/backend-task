package org.project.service.i18n.messages;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.Message;

@Localized("de")
public interface GermanAppMessages extends AppMessages {

    @Message("Armenien")
    @Override
    String ARM();

    @Message("Bulgarien")
    @Override
    String BGR();

    @Message("Kanada")
    @Override
    String CAN();

    @Message("Belgien")
    @Override
    String BEL();

    @Message("Dänemark")
    @Override
    String DNK();
}
