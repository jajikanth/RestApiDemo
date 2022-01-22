package com.company.division.api;

import com.company.division.error.GlobalExceptionHandler;
import com.company.division.service.CustomerServiceImpl;
import com.company.division.util.ErrorCode;
import com.company.division.view.CustomerInfo;
import com.company.division.view.CustomerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class CustomerApiImpTest {

	@InjectMocks
	private CustomerApiImp customerApiImp;

	@Mock
	private CustomerServiceImpl customerServiceImpl;


	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerApiImp)
                .setControllerAdvice(new GlobalExceptionHandler())
				.build();

	}

	@Test
	public void testCreateCustomerSuccess() throws Exception {
		CustomerInfo customerInfo = CustomerInfo.builder().username("jajikanth").password("Pas$W0rd")
				.ipAdress("10.0.0.0").build();

		CustomerResponse customerResponse = CustomerResponse.builder().userId(UUID.randomUUID()).username("jajikanth")
				.city("City").message("msg").build();

		Mockito.when(customerServiceImpl.createCustomer(ArgumentMatchers.any(CustomerInfo.class)))
				.thenReturn(customerResponse);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/v1/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(asJsonString(customerInfo)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(customerResponse.getUsername()));
	}

    @Test
    public void testCreateCustomerBadRequest() throws Exception {
        CustomerInfo customerInfo = CustomerInfo.builder().username("").password("sW0rd").ipAdress("")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/customer").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerInfo)).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].errorCode").value(ErrorCode.INVALID_PAYLOAD.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].errorCode").value(ErrorCode.INVALID_PAYLOAD.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].errorCode").value(ErrorCode.INVALID_PAYLOAD.name()));
              //  .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("username-must not be blank"))
             //   .andExpect(MockMvcResultMatchers.jsonPath("$[2].message").value("ipAdress-must not be blank"));

    }

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
