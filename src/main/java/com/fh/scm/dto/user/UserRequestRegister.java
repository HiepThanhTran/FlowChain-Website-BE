package com.fh.scm.dto.user;

import com.fh.scm.enums.Role;
import com.fh.scm.pojo.SupplierPaymentTerms;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestRegister {

    @NotNull(message = "{user.email.notnull}")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "{user.email.pattern}")
    private String email;

    @NotNull(message = "{user.username.notnull}")
    @Size(min = 6, max = 50, message = "{user.username.size}")
    private String username;

    @NotNull(message = "{user.password.notnull}")
    @Size(min = 8, max = 300, message = "{user.password.size}")
    private String password;

    @NotNull(message = "{user.role.notnull}")
    private Role role;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "{user.phone.pattern}")
    private String phone;

    private MultipartFile avatar;

    private String name;

    private String contactInfo;

    private String address;

    private String lastName;

    private String middleName;

    private String firstName;

    private Set<SupplierPaymentTerms> supplierPaymentTermsSet;
}