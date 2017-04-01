package com.doeritlk.yatspec.diagram;

import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer;
import com.googlecode.yatspec.state.Result;

import java.io.File;

public class SequenceDiagramResultListener implements SpecResultListener {
    private final HtmlResultRenderer htmlResultRenderer;
    private final SequenceDiagramRenderer sequenceDiagramRenderer;
    private final ImageRenderer imageRenderer;

    public SequenceDiagramResultListener(final String testCaseName) {
        htmlResultRenderer = new HtmlResultRenderer();
        sequenceDiagramRenderer = new SequenceDiagramRenderer(testCaseName);
        htmlResultRenderer.withCustomRenderer(SequenceDiagram.class, sequenceDiagramRenderer);
        imageRenderer = new ImageRenderer(testCaseName);
        htmlResultRenderer.withCustomRenderer(ImageData.class, imageRenderer);
    }

    @Override
    public void complete(final File yatspecOutputDir, final Result result) throws Exception {
        sequenceDiagramRenderer.setYatspecDir(yatspecOutputDir);
        imageRenderer.setYatspecDir(yatspecOutputDir);
        htmlResultRenderer.complete(yatspecOutputDir, result);
    }
}
