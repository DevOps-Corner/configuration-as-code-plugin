package io.jenkins.plugins.casc.plugins;

import hudson.Extension;
import hudson.model.UpdateSite;
import io.jenkins.plugins.casc.BaseConfigurator;
import io.jenkins.plugins.casc.ConfigurationContext;
import io.jenkins.plugins.casc.ConfiguratorException;
import io.jenkins.plugins.casc.model.CNode;
import io.jenkins.plugins.casc.model.Mapping;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

import javax.annotation.CheckForNull;


/**
 * TODO would  not be required if UpdateSite had a DataBoundConstructor
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
@Extension
@Restricted(NoExternalUse.class)
public class UpdateSiteConfigurator extends BaseConfigurator<UpdateSite> {

    @Override
    public Class<UpdateSite> getTarget() {
        return UpdateSite.class;
    }

    @Override
    protected UpdateSite instance(Mapping mapping, ConfigurationContext context) throws ConfiguratorException {
        return new UpdateSite(mapping.getScalarValue("id"), mapping.getScalarValue("url"));
    }

    @CheckForNull
    @Override
    public CNode describe(UpdateSite instance, ConfigurationContext context) throws Exception {
        final Mapping mapping = new Mapping();
        // TODO would need to compare with hudson.model.UpdateCenter.createDefaultUpdateSite
        // so we return null if default update site is in use.
        mapping.put("id", instance.getId());
        mapping.put("url", instance.getUrl());
        return mapping;
    }
}