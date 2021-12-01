package org.project.service.i18n.messages;

import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

/**
 * {@link MessageBundle} class, which is responsible for dealing with localization.
 * Subclasses are - {@link FrenchAppMessages} and {@link GermanAppMessages}
 *
 * @see FrenchAppMessages
 * @see GermanAppMessages
 */

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
