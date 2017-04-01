package com.doeritlk.yatspec.diagram;

public final class SequenceDiagram {
    private final byte[] diagramData;

    public SequenceDiagram(final byte[] diagramData) {
        this.diagramData = diagramData;
    }

    public byte[] getDiagramData() {
        return diagramData;
    }
}
