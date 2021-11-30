package org.project.service.i18n.messages;

import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

@MessageBundle
public interface AppMessages {

    @Message("Armenia")
    String ARM();

    @Message("Bulgaria")
    String BGR();

    @Message("Canada")
    String CAN();

    @Message("Belgium")
    String BEL();

    @Message("Denmark")
    String DNK();
}
