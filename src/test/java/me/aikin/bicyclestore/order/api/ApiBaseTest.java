package me.aikin.bicyclestore.order.api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecBuilder;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import me.aikin.bicyclestore.order.api.shopping.cart.ShoppingCartItemControllerTest;
import me.aikin.bicyclestore.order.security.principal.UserPrincipal;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public abstract class ApiBaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TruncateDatabaseService truncateDatabaseService;

    @BeforeEach
    public void setup() throws SQLException {
        truncateDatabaseService.truncate();

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    public MockMvcRequestSpecification given() {
        // TODO: should refactor
        List<GrantedAuthority> authorities = Lists.newArrayList(new SimpleGrantedAuthority(ShoppingCartItemControllerTest.RoleName.ROLE_USER.name()));
        UserPrincipal userPrincipal = UserPrincipal.builder()
            .id(1L)
            .username("aikin")
            .name("kin")
            .authorities(authorities)
            .email("aikin@me.com")
            .build();

        MockMvcRequestSpecification specification = new MockMvcRequestSpecBuilder()
            .setAuth(RestAssuredMockMvc.principal(userPrincipal))
            .build();

        return RestAssuredMockMvc
                .given()
                .spec(specification)
                .header("Accept", ContentType.JSON.withCharset("UTF-8"))
                .header("Content-Type", ContentType.JSON.withCharset("UTF-8"));
    }
}
