package com.doeritlk.yatspec;

import com.doeritlk.yatspec.diagram.SequenceDiagram;
import com.doeritlk.yatspec.diagram.SequenceDiagramResultListener;
import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.junit.WithCustomResultListeners;
import com.googlecode.yatspec.plugin.sequencediagram.ByNamingConventionMessageProducer;
import com.googlecode.yatspec.plugin.sequencediagram.PlantUmlMarkupGenerator;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramMessage;
import com.googlecode.yatspec.rendering.html.index.HtmlIndexRenderer;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public abstract class YatSpecTestBase extends TestState implements WithCustomResultListeners {

    @Rule
    public TestName testName = new TestName();
    private boolean sequenceDiagramEnabled = true;

    @After
    public void drawSequenceDiagram() throws IOException {
        if (sequenceDiagramEnabled) {
            Iterable<SequenceDiagramMessage> messages = new ByNamingConventionMessageProducer().messages(capturedInputAndOutputs);
            if (messages.iterator().hasNext()) {
                String svgMarkup = new PlantUmlMarkupGenerator().generateMarkup(messages);
                SourceStringReader sourceStringReader = new SourceStringReader(svgMarkup);
                ByteArrayOutputStream diagramImageStream = new ByteArrayOutputStream();
                sourceStringReader.generateImage(diagramImageStream, new FileFormatOption(FileFormat.PNG));
                byte[] diagramBytes = diagramImageStream.toByteArray();
                diagramImageStream.close();

                capturedInputAndOutputs.add(sequenceDiagramName(), new SequenceDiagram(diagramBytes));
            }
        }
    }

    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        return Arrays.asList(new SequenceDiagramResultListener(testName.getMethodName()), new HtmlIndexRenderer());
    }

    @SuppressWarnings("WeakerAccess")
    protected String sequenceDiagramName() {
        return "Sequence Diagram";
    }

    protected void disableSequenceDiagram() {
        this.sequenceDiagramEnabled = false;
    }

    protected void enableSequenceDiagram() {
        this.sequenceDiagramEnabled = true;
    }
}
