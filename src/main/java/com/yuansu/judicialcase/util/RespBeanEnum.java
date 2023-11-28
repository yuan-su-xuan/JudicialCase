package com.yuansu.judicialcase.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
* 公共返回对象的枚举
* */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    LOGIN_ERROR(500210,"登录失败"),
    EMAIL_ERROR(500211,"邮箱格式不正确"),
    BIND_ERROR(500212, "参数校验异常"),
    SESSION_ERROR(500215, "账号异常，请联系管理员"),
    REGISTER_INPUT_ERROR(500216, "密码必须是字母数字组合并且不小于6位"),
    REGISTER_LONG_ERROR(500217, "密码过长,请重新输入"),
    REGISTER_TEST_ERROR(500218, "密码输入不一致"),
    REGISTER_REPETITION_ERROR(500219, "该邮件已存在"),
    REGISTER_ERROR(500220, "注册失败"),
    LOGIN_VALID_ERROR(500221, "登录失败!请前往邮箱激活"),
    ACTIVATION_TIME_ERROR(500222, "链接已失效，请重新注册"),
    ACTIVATION_ERROR(500223, "激活失败"),
    ACTIVATION_REPETITION_ERROR(500224, "已注册!请前往邮箱激活"),
    DELETE_USER_ERROR(500225, "删除失败!"),
    EMAIL_USER_ERROR(500226,"该邮箱用户不存在或未认证"),
    RESET_PASSWORD_CODE_ERROR(500227,"验证码错误"),
    REQUEST_ILLEGAL(500502, "请求非法，请重新尝试"),
    ERROR_CAPTCHA(500503, "验证码错误，请重新输入"),
    ACCESS_LIMIT_REACHED(500504, "访问过于频繁，请稍后重试"),
    FILE_NOT_EXIST(500505, "文件不存在"),
    LITERATURE_UPLOAD_FAIL(500506, "文献上传失败"),
    FILE_DOWNLOAD_FAIL(500507, "文件下载失败"),
    FILE_UPLOAD_FAIL(5005013, "文件上传失败"),
    LITERATURE_DELETE_FAIL(500508, "文献删除失败"),
    LITERATURE_UPDATE_FAIL(500509, "文献更新失败"),
    SUBPROJECT_DELETE_FAIL(500510,"子项目删除失败"),
    PROJECT_ADD_FAIL(500511,"项目创建失败"),
    LITERATURE_ILLEGAL_FAIL(500512, "文献格式不合法"),
    LITERATURE_PRO_UPDATE_FAIL(500520, "文献已入库，无法修改"),
    EXTRACT_DELETE_FAIL(500601,"提取项删除失败"),
    EXTRACT_ADD_FAIL(500602,"提取项添加失败"),
    USER_ADD_FAIL(500701,"成员添加失败"),
    ALLOCATION_SET_FAIL(500801,"分配失败"),
    ALLOCATION_SUBMIT_FAIL(500802,"提交失败"),
    ALLOCATION_NULL(500803,"未查询到提取项"),
    ALLOCATION_COMMIT(500804,"提取项已提交，无法更改"),
    MERGE_NULL(500901,"合并失败"),
    MERGE_COMMIT(500902,"合并已提交，无法更改"),
    CONCLUSION_ADD_FAIL(500951,"结论添加失败"),
    CONCLUSION_COMMIT(500952,"结论已提交，无法更改"),
    PROJECT_UPDATE_FAIL(500901,"项目更新失败"),
    ADD_MEMBER_FAIL(500902,"添加成员失败"),
    PROTOCOL_UPLOAD_FAIL(500903,"protocol上传失败")
    ;
    private final int code;
    private final String message;

}
