package WithMockVendor;

import com.example.board.WithMockVendorUserSecurityContextFactory;
import org.springframework.core.annotation.AliasFor;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockVendorUserSecurityContextFactory.class)
public @interface WithMockVendorUser {
    String value() default "user";

    String username() default "user";

    String[] roles() default {"USER"};

    String[] authorities() default {};

    String password() default "password";

    String name() default "hoho";

    @AliasFor(
            annotation = WithSecurityContext.class
    )
    TestExecutionEvent setupBefore() default TestExecutionEvent.TEST_METHOD;
}
