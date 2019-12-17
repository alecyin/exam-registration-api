package com.exam.registration.controller;

import com.exam.registration.model.Site;
import com.exam.registration.service.SiteService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
    public String insertSite(Site site) {
        if (StringUtils.isEmpty(site.getName())) {
            return MsgUtils.fail("考点名称不能为空");
        }

        if (StringUtils.isEmpty(site.getCode())) {
            return MsgUtils.fail("代号不能为空");
        }

        if (StringUtils.isEmpty(site.getAddress())) {
            return MsgUtils.fail("考点地址不能为空");
        }

        Site querySite = siteService.getSiteByCode(site.getCode());
        if (Objects.nonNull(querySite)) {
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
        Site site = new Site();
        site.setId(id);
        site.setIsDeleted(true);
        int res = siteService.updateSiteByPrimaryKeySelective(site);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateSite(Site site) {
        int res = siteService.updateSiteByPrimaryKeySelective(site);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listSites() {
        List<Site> list = siteService.listSites();
        return MsgUtils.success(list);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getSiteByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(siteService.getSiteByPrimaryKey(id));
    }
}
