package com.bits.kata;

import com.bits.kata.model.RunMatch;
import com.bits.kata.repositories.GameScoreRepository;
import com.bits.kata.repositories.MatchSetsRepository;
import com.bits.kata.repositories.RunMatchRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean. Use the @PWA
 * annotation make the application installable on phones, tablets and some
 * desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 *
 * @author Rivo
 */
@Route
@PWA(name = "Kata Tennis with Vaadin App",
        shortName = "Kata Tennis App",
        description = "Technical test for job hiring",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class KataTennisMainView extends VerticalLayout {

    @Autowired
    private GameInitializerService initService;

    @Autowired
    private GamePointRegistrationService service;

    @Autowired
    private RunMatchRepository matchRepository;

    @Autowired
    private MatchSetsRepository mSetsRepository;

    @Autowired
    private GameScoreRepository gScoreRepository;

    private Grid<RunMatch> mGrid;

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     */
    public KataTennisMainView() {

        // Use TextField for standard text input
        TextField p1Field = new TextField("Player 1 : ");
        TextField p2Field = new TextField("Player 1 : ");

        // Button click listeners can be defined as lambda expressions
        Button button = new Button("Create a tennis new match instance",
                e -> {
                    final RunMatch createdGame = initService.createNewGame(p1Field.getValue(), p2Field.getValue(), YesNoEnum.N, YesNoEnum.N);
                    String matchDesc = createdGame.toString();
                    Notification.show("Created game : " + matchDesc);
                    getMatchGrid().setItems(matchRepository.findAll());
                });

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(p1Field, p2Field, button, getMatchGrid());
    }

    private Grid<RunMatch> getMatchGrid() {
        if (mGrid == null) {
            mGrid = new Grid<>(RunMatch.class);
            mGrid.setWidthFull();
            mGrid.setSizeFull();
            mGrid.setColumns("id", "player1", "player2", "winner", "enableDeuceRule", "enableTieBreakRule");
        }

        return mGrid;
    }

}
