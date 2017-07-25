package tk.ladyka.andrei.admin.smoketest;

import tk.ladyka.andrei.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RestTestGetAllForAllControllers {
    private static final int timeoutOneTest = 10000;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test(timeout = timeoutOneTest)
    public void bodytypeGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/bodytype/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void categoryGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/category/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void categorypartGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/categorypart/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void colorfilterGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/colorfilter/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void colormapGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/colormap/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void configurationreferGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/configurationrefer/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void desktopimageGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/desktopimage/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void desktopimagedetailGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/desktopimagedetail/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void desktopstaticdetailGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/desktopstaticdetail/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void eventtrackingGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/eventtracking/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void imagetransferGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/imagetransfer/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void makeGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/make/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void mediaGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/media/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void modelGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/model/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void modeldefaultGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/modeldefault/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void modeltrimuseGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/modeltrimuse/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void notificationGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/notification/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void optiongroupGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/optiongroup/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void partGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/part/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void parttrimGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/parttrim/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void parttrimmediaGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/parttrimmedia/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void rulecategoryGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/rulecategory/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void ruleGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/rule/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void rulegroupGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/rulegroup/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void ruleparttrimGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/ruleparttrim/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void sqlGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/sql/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void subpartGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/subpart/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void trimGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/trim/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void trimmediaGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/trimmedia/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void trimuseGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/trimuse/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void useaccessGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/useaccess/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void useGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/use/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void vehicleGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/vehicle/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void vehiclecountGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/vehiclecount/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void vehiclemediaGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/vehiclemedia/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void vehiclenameGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/vehiclename/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void vehiclepartGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/vehiclepart/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void vehiclepollGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/vehiclepoll/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void vehiclerecordtypeGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/vehiclerecordtype/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void versionGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/version/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }

    @Test(timeout = timeoutOneTest)
    public void xmlcacheGetAll() {
        String s = "fail";
        try {
            MvcResult mvcResult = mvc.perform(get("/rest/configurator/xmlcache/").with(user("dev@beamland.com").roles("ADMIN", "SEC_TOOL"))).andReturn();
            s = mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(s != "fail");
    }
}
