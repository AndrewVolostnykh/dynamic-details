/**
 * <p>This package contains essential utility classes designed to be used within
 * COMPUTABLE type expressions of the {@link andrew.volostnykh.dddemo.model.Detail} class. These utilities
 * serve various business-related operations, such as value retrieval by object,
 * rounding, current date calculation, and other necessary functions.
 * </p>
 * <p>For security purposes, all beans should be registered within the
 * {@link andrew.volostnykh.dddemo.service.detail.DetailProcessorContext}.
 * This context ensures that only approved beans
 * can be utilized in SpEL (Spring Expression Language) expressions. This approach
 * helps prevent arbitrary beans from being accessed from the {@link org.springframework.context.ApplicationContext}.
 * </p>
 * By using this package, we maintain a controlled and secure environment for
 * business logic operations while leveraging SpEL expressions.
 */
package andrew.volostnykh.dddemo.service.detail.utils;