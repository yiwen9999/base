package com.hex.base.controller;

import com.hex.base.domain.Operator;
import com.hex.base.domain.Role;
import com.hex.base.service.OperatorService;
import com.hex.base.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 页面跳转controller
 */
@Controller
public class PageController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private OperatorService operatorService;

    @Value("${web.url}")
    private String URL;

    @GetMapping(value = "/")
    public String home() {
        return "redirect:" + URL;
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "desktop";
    }

    /**
     * 跳转登录页面
     *
     * @return
     */
    @GetMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 跳转修改密码
     *
     * @return
     */
    @GetMapping(value = "/toPasswordUpdate")
    public String toPasswordUpdate() {
        return "/passwordUpdate";
    }

    /**
     * 跳转操作人员列表页面
     *
     * @return
     */
    @GetMapping(value = "/toOperatorList")
    public String toOperatorList() {
        return "/operator/operatorList";
    }

    /**
     * 跳转添加操作人员页面
     *
     * @return
     */
    @GetMapping(value = "/toOperatorAdd")
    public String toOperatorAdd() {
        return "/operator/operatorAdd";
    }

    /**
     * 跳转修改操作人员页面
     *
     * @return
     */
    @GetMapping(value = "/toOperatorUpdate")
    public String toOperatorUpdate(String id, Model model) {
        Operator operator = operatorService.findOperatorById(id);
        model.addAttribute("operator", operator);
        return "/operator/operatorUpdate";
    }

    /**
     * 跳转频道树页面
     *
     * @return
     */
    @GetMapping(value = "/toChannelTree")
    public String toChannelTree() {
        return "/channel/channelTree";
    }

    /**
     * 跳转角色列表页面
     *
     * @return
     */
    @GetMapping(value = "/toRoleList")
    public String toRoleList() {
        return "/role/roleList";
    }

    /**
     * 跳转添加角色页面
     *
     * @return
     */
    @GetMapping(value = "/toRoleAdd")
    public String toRoleAdd() {
        return "/role/roleAdd";
    }

    /**
     * 根据角色id跳转修改角色页面
     *
     * @param model 页面传递：角色（role）；角色所选频道集合（roleChannels）
     * @param id    角色id
     * @return
     */
    @GetMapping(value = "/toRoleUpdate")
    public String toRoleUpdate(Model model, @RequestParam String id) {
        Role role = roleService.findRoleById(id);
        model.addAttribute("role", role);
        model.addAttribute("roleChannels", role.getChannels());
        return "/role/roleAdd";
    }

    /**
     * 根据角色id跳转角色所选频道浏览页
     *
     * @param model  页面传递：角色id（roleId）
     * @param roleId 角色id
     * @return
     */
    @GetMapping(value = "/toRoleChannelView")
    public String toRoleChannelView(Model model, @RequestParam String roleId) {
        model.addAttribute("roleId", roleId);
        return "/role/roleChannelView";
    }

    /**
     * 跳转讲者列表
     *
     * @return
     */
    @GetMapping("/toSpeakerList")
    public String toSpeakerList() {
        return "/speaker/speakerList";
    }

    /**
     * 跳转讲者添加&修改
     *
     * @return
     */
    @GetMapping("/toSpeakerAdd")
    public String toSpeakerAdd(@RequestParam(required = false) Integer speakerId, Model model) {
        model.addAttribute("speakerId", speakerId);
        return "/speaker/speakerAdd";
    }

    /**
     * 跳转商品列表
     *
     * @return
     */
    @GetMapping("/toProductList")
    public String toProductList() {
        return "/product/productList";
    }

    /**
     * 跳转商品添加&修改
     *
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/toProductAdd")
    public String toProductAdd(@RequestParam(required = false) Integer productId, Model model) {
        model.addAttribute("productId", productId);
        return "/product/productAdd";
    }

    /**
     * 跳转视频分类列表
     *
     * @return
     */
    @GetMapping("/toCourseCategoryList")
    public String toCourseCategoryList() {
        return "/courseCategory/courseCategoryList";
    }

    /**
     * 跳转视频分类添加&修改
     *
     * @param courseCategoryId
     * @param model
     * @return
     */
    @GetMapping("/toCourseCategoryAdd")
    public String toCourseCategoryAdd(@RequestParam(required = false) Integer courseCategoryId, Model model) {
        model.addAttribute("courseCategoryId", courseCategoryId);
        return "/courseCategory/courseCategoryAdd";
    }

    /**
     * 跳转视频列表
     *
     * @return
     */
    @GetMapping("/toCourseList")
    public String toCourseList() {
        return "/course/courseList";
    }

    /**
     * 跳转视频添加&修改
     *
     * @param courseId
     * @param model
     * @return
     */
    @GetMapping("/toCourseAdd")
    public String toCourseAdd(@RequestParam(required = false) Integer courseId, Model model) {
        model.addAttribute("courseId", courseId);
        return "/course/courseAdd";
    }
}
