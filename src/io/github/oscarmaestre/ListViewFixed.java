package io.github.oscarmaestre;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Skin;

public class ListViewFixed<T> extends javafx.scene.control.ListView<T>
{
    // <editor-fold defaultstate="collapsed" desc="Properties">
    private final BooleanProperty fillWidth = new SimpleBooleanProperty(this, "fillWidth");

    public final BooleanProperty fillWidthProperty()
    {
        return fillWidth;
    }

    public final boolean isFillWidth()
    {
        return fillWidth.get();
    }

    public final void setFillWidth(boolean fillWidth)
    {
        this.fillWidth.set(fillWidth);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    protected Skin createDefaultSkin()
    {
        return new ListViewFixedSkin(this);
    }
    // </editor-fold>
}