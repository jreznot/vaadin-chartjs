package com.byteowls.vaadin.chartjs.options.scale;

import com.byteowls.vaadin.chartjs.utils.JUtils;
import com.byteowls.vaadin.chartjs.utils.JsonBuilder;
import elemental.json.Json;
import elemental.json.JsonObject;

/**
 * @author michael@team-conductor.com
 */
public abstract class BaseScale<B extends BaseScale<?>> implements JsonBuilder {

    public enum Position {
        TOP, RIGHT, BOTTOM, LEFT
    }

    private String id;
    protected String type;
    protected Boolean display;
    protected Position position;
    protected Boolean stacked;
    protected GridLines<B> gridLines;
    protected Ticks<B> ticks;
    protected ScaleLabel<B> scaleLabel;

    /**
     * "category", "linear", "logarithmic", "time", "radialLinear"
     */
    public BaseScale<B> type(String type) {
        this.type = type;
        return this;
    }

    /**
     * If true, show the scale including gridlines, ticks, and labels. Overrides gridLines.display, scaleLabel.display, and ticks.display.
     */
    public BaseScale<B> display(boolean display) {
        this.display = display;
        return this;
    }

    /**
     * The ID is used to link datasets and scale axes together. The properties `datasets.xAxisID` or `datasets.yAxisID` have to match the scale properties `scales.xAxes.id` or `scales.yAxes.id`. This is especially needed if multi-axes charts are used.
     */
    public BaseScale<B> id(String id) {
        this.id = id;
        return this;
    }

    /**
     * If true, bars are stacked on the x-axis
     */
    public BaseScale<B> stacked(boolean stacked) {
        this.stacked = stacked;
        return this;
    }

    /**
     * Position of the scale.
     */
    public BaseScale<B> position(Position position) {
        this.position = position;
        return this;
    }

    /**
     * It defines options for the grid lines that run perpendicular to the axis.
     */
    public GridLines<B> gridLines() {
        if (gridLines == null) {
            gridLines = new GridLines<>(getThis());
        }
        return gridLines;
    }

    /**
     * Define options for the scale title.
     */
    public ScaleLabel<B> scaleLabel() {
        if (scaleLabel == null) {
            scaleLabel = new ScaleLabel<>(getThis());
        }
        return scaleLabel;
    }

    /**
     * It defines options for the tick marks that are generated by the axis.
     */
    public Ticks<B> ticks() {
        if (ticks == null) {
            ticks = new Ticks<>(getThis());
        }
        return ticks;
    }

    public abstract B getThis();


    @Override
    public JsonObject buildJson() {
        JsonObject map = Json.createObject();
        JUtils.putNotNull(map, "type", type);
        JUtils.putNotNull(map, "display", display);
        JUtils.putNotNull(map, "id", id);
        JUtils.putNotNull(map, "stacked", stacked);
        if (position != null) {
            JUtils.putNotNull(map, "position", position.name().toLowerCase());
        }
        JUtils.putNotNull(map, "gridLines", gridLines);
        JUtils.putNotNull(map, "scaleLabel", scaleLabel);
        JUtils.putNotNull(map, "ticks", ticks);
        return map;
    }
}
