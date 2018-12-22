package com.marchenkoteam.kotlinlearning.controllers

import com.marchenkoteam.kotlinlearning.forms.SkillForm
import com.marchenkoteam.kotlinlearning.forms.ThemeForm
import com.marchenkoteam.kotlinlearning.services.ThemeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/themes")
class ThemeController @Autowired constructor(private val themeService: ThemeService) {

    @GetMapping
    fun list() = themeService.findAll()

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    fun create(@RequestHeader authToken: String, @RequestBody themeForm: ThemeForm) = themeService.save(themeForm)

    @GetMapping("/{id}")
    fun retrieve(@PathVariable("id") id: Long) = themeService.findById(id)

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    fun update(@RequestHeader authToken: String, @RequestBody themeForm: ThemeForm,
               @PathVariable("id") id: Long) = themeService.save(themeForm)

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@RequestHeader authToken: String, @PathVariable("id") id: Long) = themeService.deleteById(id)

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/skill")
    fun addSkill(@PathVariable("id") id: Long, @RequestHeader authToken: String,
                 @RequestBody skillForm: SkillForm) = themeService.addSkill(skillForm, id)

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/skill")
    fun updateSkill(@PathVariable("id") id: Long, @RequestHeader authToken: String,
                    @RequestBody skillForm: SkillForm) = themeService.updateSkill(skillForm, id)

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}/skill")
    fun deleteSkill(@PathVariable("id") id: Long, @RequestHeader authToken: String,
                    @RequestBody skillForm: SkillForm) = themeService.deleteSkill(skillForm, id)
}