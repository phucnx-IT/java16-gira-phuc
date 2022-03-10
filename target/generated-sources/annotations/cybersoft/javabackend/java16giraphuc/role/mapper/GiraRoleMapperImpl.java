package cybersoft.javabackend.java16giraphuc.role.mapper;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole.GiraRoleBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-10T15:37:56+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class GiraRoleMapperImpl implements GiraRoleMapper {

    @Override
    public GiraRole mapToEntity(GiraRoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GiraRoleBuilder<?, ?> giraRole = GiraRole.builder();

        giraRole.code( dto.getCode() );
        giraRole.description( dto.getDescription() );

        return giraRole.build();
    }
}
