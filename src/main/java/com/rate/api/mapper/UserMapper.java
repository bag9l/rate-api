package com.rate.api.mapper;

import com.rate.api.dto.LecturerDto;
import com.rate.api.dto.LecturerProfile;
import com.rate.api.dto.StudentProfile;
import com.rate.api.dto.UserView;
import com.rate.api.dto.auth.AuthenticatedUser;
import com.rate.api.model.user.Lecturer;
import com.rate.api.model.user.Student;
import com.rate.api.model.user.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {FeedbackMapper.class})
public abstract class UserMapper {

    protected FeedbackMapper feedbackMapper;

    @Mapping(target = "role", source = "role.value")
    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.rate.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract AuthenticatedUser userToAuthenticatedUser(User user);

    @Mapping(target = "degree", source = "degree.value")
    public abstract LecturerDto lecturerToDto(Lecturer lecturer);

    @Mapping(target = "degree", source = "degree.value")
    @Mapping(target = "department", source = "department.name")
    @Mapping(target = "faculty", source = "department.faculty.name")
    @Mapping(target = "avatar", expression = "java((lecturer.getAvatar() != null) ? " +
            "com.rate.api.util.ImageUtil.decompressImage(lecturer.getAvatar().getImageData()) : null)")
    @Mapping(target = "feedbacks",
            expression = "java(lecturer.getFeedbacks().stream()" +
                    ".map(feedback -> feedbackMapper.feedbackToDto(feedback))" +
                    ".collect(java.util.stream.Collectors.toList()))")
    public abstract LecturerProfile lecturerToLecturerProfile(Lecturer lecturer);

    @Mapping(target = "degree", source = "group.stream.course.degree.value")
    @Mapping(target = "group", source = "group.name")
    @Mapping(target = "faculty", source = "group.stream.course.faculty.name")
    @Mapping(target = "avatar", expression = "java((student.getAvatar() != null) ? " +
            "com.rate.api.util.ImageUtil.decompressImage(student.getAvatar().getImageData()) : null)")
    public abstract StudentProfile studentToStudentProfile(Student student);

    @Mapping(target = "avatar", expression = "java((user.getAvatar()!=null) " +
            "? com.rate.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()):null)")
    public abstract UserView userToUserView(User user);

    @Autowired
    public void setFeedbackMapper(@Lazy FeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

}
