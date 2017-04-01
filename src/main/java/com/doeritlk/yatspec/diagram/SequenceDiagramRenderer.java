package com.doeritlk.yatspec.diagram;

import com.googlecode.yatspec.rendering.Renderer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class SequenceDiagramRenderer implements Renderer<SequenceDiagram> {
    private static final Random PRNG = new Random();
    private File yatspecDir;
    private String testCaseName;

    public SequenceDiagramRenderer(final String testCaseName) {
        this.testCaseName = testCaseName;
    }

    @Override
    public String render(final SequenceDiagram sequenceDiagram) throws Exception {
        Path sequenceDiagramFile = filePath();
        Files.write(sequenceDiagramFile, sequenceDiagram.getDiagramData(), StandardOpenOption.CREATE);

        return String.format("<div class='nohighlight'><img src=\"%s\"></img></div>", sequenceDiagramFile.toString());
    }

    public void setYatspecDir(final File yatspecDir) {
        this.yatspecDir = yatspecDir;
    }

    private Path filePath() {
        return Paths.get(yatspecDir.getPath(), File.separator, testCaseName + PRNG.nextInt() + ".png");
    }
}
