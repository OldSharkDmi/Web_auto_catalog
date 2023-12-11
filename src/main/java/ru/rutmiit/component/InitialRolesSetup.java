package ru.rutmiit.component;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.models.Enum.RoleEnum;
import ru.rutmiit.models.UserRole;
import ru.rutmiit.repositories.UserRoleRepository;

@Component
public class InitialRolesSetup implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRoleRepository userRoleRepository;

    public InitialRolesSetup(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createRoleIfNotFound(RoleEnum.User);
        createRoleIfNotFound(RoleEnum.Admin);
    }

    private void createRoleIfNotFound(RoleEnum roleEnum) {
        if (userRoleRepository.findByRoleEnum(roleEnum).isEmpty()) {
            UserRole role = new UserRole();
            role.setRoleEnum(roleEnum);
            userRoleRepository.save(role);
        }
    }
}
