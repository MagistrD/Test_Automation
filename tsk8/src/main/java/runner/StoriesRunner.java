package runner;

import org.jbehave.core.Embeddable;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import report.StoryReportBuilder;
import steps.*;

import java.util.Arrays;
import java.util.List;


@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyReporterBuilder = StoryReportBuilder.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, metaFilters = "-skip")
@UsingSteps(instances = {AliLoginSteps.class, AliNavigationSteps.class, AliBrandSearchSteps.class, AliProductSteps.class,
        AliCartSteps.class})
public class StoriesRunner implements Embeddable {
    private Embedder embedder;

    public void useEmbedder(Embedder embedder) {
        this.embedder = embedder;
    }

    @Test
    public void run() {
        embedder.runStoriesAsPaths(storyPath());
    }

    public List<String> storyPath() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()).getFile(),
                Arrays.asList("**/*.story"), Arrays.asList(""));
    }
}
