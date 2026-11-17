package ru.rshb.intech.digital.notifications.mail.template;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import jakarta.annotation.Nonnull;
import java.util.Locale;
import java.util.Map;

@Component
public class HtmlTemplateConverter {

  private static final String TEMPLATE_DIR = "template";

  private final ThymeleafTemplateEngine resourceTemplateEngine;
  private final ThymeleafTemplateEngine stringTemplateEngine;


  public HtmlTemplateConverter() {
    this.resourceTemplateEngine = new ResourcesTemplateEngine(TEMPLATE_DIR);
    this.stringTemplateEngine = new StringTemplateEngine();
  }

  @Nonnull
  public String toHtmlFromResource(@Nonnull String templateResourceName, @Nonnull Map<String, Object> values) {
    final var engineWrapper = resourceTemplateEngine;
    final var context = engineWrapper.newContext(values);
    return engineWrapper.getTemplateEngine()
        .process(templateResourceName, context);
  }

  @Nonnull
  public String toHtmlFromString(@Nonnull String template, @Nonnull Map<String, Object> values) {
    final var engineWrapper = stringTemplateEngine;
    final var context = engineWrapper.newContext(values);
    return engineWrapper.getTemplateEngine()
        .process(template, context);
  }


  private interface ThymeleafTemplateEngine {

    @Nonnull
    TemplateEngine getTemplateEngine();

    @Nonnull
    default Context newContext(@Nonnull Map<String, Object> variables) {
      return new Context(Locale.getDefault(), variables);
    }

  }

  @Getter
  private static class StringTemplateEngine implements ThymeleafTemplateEngine {

    private final TemplateEngine templateEngine;

    public StringTemplateEngine() {
      this.templateEngine = templateEngine();
    }

    @Nonnull
    private TemplateEngine templateEngine() {
      final var engine = new TemplateEngine();
      final var resolver = new StringTemplateResolver();
      engine.setTemplateResolver(resolver);
      return engine;
    }
  }

  @Getter
  private static class ResourcesTemplateEngine implements ThymeleafTemplateEngine {

    private final TemplateEngine templateEngine;

    public ResourcesTemplateEngine(@Nonnull String templateDirPath) {
      this.templateEngine = templateEngine(templateDirPath);
    }

    @Nonnull
    private TemplateEngine templateEngine(@Nonnull String templateDirPath) {
      final var engine = new TemplateEngine();
      final var resolver = new ClassLoaderTemplateResolver();
      resolver.setPrefix(templateDirPath);
      resolver.setTemplateMode(TemplateMode.HTML);
      engine.setTemplateResolver(resolver);
      return engine;
    }
  }
}
