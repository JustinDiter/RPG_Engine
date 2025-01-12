package com.justinditer.rpgengine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class MenuUI extends Table {

    // A Menu UI overlay attempt. Spent a lot of time here trying to make it work, but got stuck frequently.
    // Would implement in a different way if I were to redo the project.

    private Array<String> menuOptions;
    private Skin skin;

    public MenuUI() {

        // This is the Menu that opens when the player presses "ESC" when in the game world.

        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        setFillParent(false);
        align(Align.left);
        setPosition(0, (float) Gdx.graphics.getHeight() * 0.75f);
        pad(20);
        menuOptions = new Array<>(new String[]{"  Stats  ", "  Inventory  ", "  Exit  "});
        for (String option : menuOptions) {
            Button button = new TextButton(option, skin);
            add(button).pad(5).align(Align.center);
            row();
        }
    }
}
