package com.rate.api.mapper;

import com.rate.api.dto.LecturerDto;
import com.rate.api.dto.LecturerProfile;
import com.rate.api.dto.UserView;
import com.rate.api.dto.auth.AuthenticatedUser;
import com.rate.api.model.Lecturer;
import com.rate.api.model.User;
import com.rate.api.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StatisticMapper.class, ImageService.class})
public abstract class UserMapper {

    protected StatisticMapper statisticMapper;
    protected ImageService imageService;

    @Mapping(target = "role", source = "role.value")
    @Mapping(target = "avatar", expression = "java((user.getAvatar()!=null) ? " +
            "imageService.decompressImage(user.getAvatar().getImageData()):null)")
    public abstract AuthenticatedUser userToAuthenticatedUser(User user);

    @Mapping(target = "degree", source = "degree.value")
    public abstract LecturerDto lecturerToDto(Lecturer lecturer);

    @Mapping(target = "degree", source = "degree.value")
    @Mapping(target = "department", source = "department.name")
    @Mapping(target = "statistics",
            expression = "java(lecturer.getStatistics().stream()" +
                    ".map(statistic -> statisticMapper.statisticToDto(statistic))" +
                    ".collect(java.util.stream.Collectors.toList()))")
    public abstract LecturerProfile lecturerToLecturerProfile(Lecturer lecturer);

    @Mapping(target = "avatar", expression = "java((user.getAvatar()!=null) " +
            "? imageService.decompressImage(user.getAvatar().getImageData()):null)")
    public abstract UserView userToUserView(User user);

    @Autowired
    public void setStatisticMapper(@Lazy StatisticMapper statisticMapper) {
        this.statisticMapper = statisticMapper;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

}
