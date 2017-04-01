package com.doeritlk.yatspec.diagram;

import com.googlecode.yatspec.rendering.Renderer;

import java.io.File;
import java.nio.file.Path;

public class ImageRenderer implements Renderer<ImageData>, HasFileWriter {

    private File yatspecDir;
    private final String testCaseName;

    public ImageRenderer(final String testCaseName) {
        this.testCaseName = testCaseName;
    }

    @Override
    public String render(final ImageData imageData) throws Exception {
        Path path = write(imageData.getImage(), yatspecDir.getAbsolutePath(), File.pathSeparator + testCaseName + System.currentTimeMillis() + ".png");
        return String.format("<div class='nohighlight'><img src=\"%s\"></img></div>", path.toAbsolutePath().toString());
    }

    public void setYatspecDir(final File yatspecDir) {
        this.yatspecDir = yatspecDir;
    }
}
