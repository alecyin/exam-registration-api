package com.exam.registration.controller;

import com.exam.registration.model.Site;
import com.exam.registration.service.SiteService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname SiteController
 * @description TODO
 * @date 2019/12/17
 **/
@RequestMapping("/sites")
@Controller
public class SiteController {
    @Autowired
    private SiteService siteService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertSite(@RequestBody Site site) {
        if (StringUtils.isEmpty(site.getName())) {
            return MsgUtils.fail("考点名称不能为空");
        }
        if (StringUtils.isEmpty(site.getCode())) {
            return MsgUtils.fail("代号不能为空");
        }
        if (StringUtils.isEmpty(site.getAddress())) {
            return MsgUtils.fail("考点地址不能为空");
        }

        if (Objects.nonNull(siteService.getSiteByCode(site.getCode()))) {
            return MsgUtils.fail("代号重复");
        }

        int res = siteService.insertSite(site);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteSite(@PathVariable("id") long id) {
        int res = siteService.deleteSiteByPrimaryKey(id);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllSite(@RequestParam("ids") String ids) {
        int res = siteService.deleteSiteByPrimaryKeys(ids);
        return res == 0 ? MsgUtils.fail("删除失败，稍后再试") : MsgUtils.success();
    }

    public String softDeleteSite(@PathVariable("id") long id) {
        Site site = new Site();
        site.setId(id);
        site.setIsDeleted(true);
        int res = siteService.updateSiteByPrimaryKeySelective(site);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateSite(@RequestBody Site site) {
        int res = siteService.updateSiteByPrimaryKeySelective(site);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(path = "/enabled", method = RequestMethod.GET)
    @ResponseBody
    public String listSites() {
        List<Site> list = siteService.listSites();
        return MsgUtils.success(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listSitesByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                     @RequestParam("pageIndex") int pageIndex,
                                     @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("keyword", keyword);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Site> list = siteService.listSitesByPage(map);
        long pageTotal = siteService.countSites(keyword);
        return MsgUtils.querySuccess(list, pageTotal);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getSiteByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(siteService.getSiteByPrimaryKey(id));
    }
}
