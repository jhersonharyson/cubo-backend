package br.com.totvs.zeus.api.service;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.totvs.summer.core.exception.ServiceException;

@RunWith(Arquillian.class)
public class ZeusServiceIT {

    @Inject
    private UsuarioService usuarioService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(ZipImporter.class, "zeus-api-teste.war")
                .importFrom(new File("target/zeus-api.war"))
                .as(WebArchive.class);
    }

    @Test
    public void usuarioInexistentes() {
        try {
            Assert.assertFalse(usuarioService.retornaUsuarioPorLogin("a.ohana") == null);
        } catch (ServiceException e) {
            Assert.fail();
        }
    }

}