/**
 * Module for the IT Worker application.
 */
module mycash {
    requires adopt.core;
    requires adopt.javafx.application;
    requires slf4j.api;
    requires com.google.common;

    exports dk.sunepoulsen.mycash;
}