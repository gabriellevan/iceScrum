<%@ page import="org.icescrum.core.utils.BundleUtils" %>
%{--
- Copyright (c) 2014 Kagilum.
-
- This file is part of iceScrum.
-
- iceScrum is free software: you can redistribute it and/or modify
- it under the terms of the GNU Affero General Public License as published by
- the Free Software Foundation, either version 3 of the License.
-
- iceScrum is distributed in the hope that it will be useful,
- but WITHOUT ANY WARRANTY; without even the implied warranty of
- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
- GNU General Public License for more details.
-
- You should have received a copy of the GNU Affero General Public License
- along with iceScrum.  If not, see <http://www.gnu.org/licenses/>.
-
- Authors:
-
- Vincent Barrier (vbarrier@kagilum.com)
- Nicolas Noullet (nnoullet@kagilum.com)
--}%
<script type="text/ng-template" id="actor.details.html">
<div class="panel panel-default">
    <div class="panel-heading"
         fixed="#right"
         fixed-offset-top="1"
         fixed-offset-width="-2">
        <h3 class="panel-title row">
            <div class="the-title">
                <span>{{ actor.name }}</span>
            </div>
            <div class="the-id">
                <div class="pull-right">
                    <span class="label label-default"
                          tooltip="${message(code: 'is.backlogelement.id')}">{{ actor.uid }}</span>
                    <a ng-if="previous"
                       class="btn btn-xs btn-default"
                       role="button"
                       tabindex="0"
                       href="#actor/{{ previous.id }}"><i class="fa fa-caret-left" title="${message(code:'is.ui.backlogelement.toolbar.previous')}"></i></a>
                    <a ng-if="next"
                       class="btn btn-xs btn-default"
                       role="button"
                       tabindex="0"
                       href="#actor/{{ next.id }}"><i class="fa fa-caret-right" title="${message(code:'is.ui.backlogelement.toolbar.next')}"></i></a>
                </div>
            </div>
        </h3>
        <div class="actions">
            <div class="btn-group"
                 ng-if="authorizedActor('menu')"
                 tooltip="${message(code: 'todo.is.ui.actions')}"
                 tooltip-append-to-body="true">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <span class="fa fa-cog"></span> <span class="caret"></span>
                </button>
                <ul class="dropdown-menu"
                    ng-include="'actor.menu.html'"></ul>
            </div>
            <button type="button"
                    tabindex="-1"
                    popover-title="${message(code:'is.permalink')}"
                    popover="{{ serverUrl + '/TODOPKEY-A' + actor.uid }}"
                    popover-append-to-body="true"
                    popover-placement="left"
                    class="btn btn-default">
                <i class="fa fa-link"></i>
            </button>
           <div class="btn-group pull-right">
                <button name="attachments" class="btn btn-default"
                        ng-click="setTabSelected('attachments')"
                        tooltip="{{ actor.attachments.length }} ${message(code:'todo.is.backlogelement.attachments')}"
                        tooltip-append-to-body="true">
                    <span class="fa fa-paperclip"></span>
                    <span class="badge" ng-show="actor.attachments_count">{{ actor.attachments_count }}</span>
                </button>
                <button name="stories" class="btn btn-default"
                        ng-click="setTabSelected('stories')"
                        tooltip="{{ actor.stories_count }} ${message(code:'todo.is.actor.stories')}"
                        tooltip-append-to-body="true">
                    <span class="fa fa-tasks"></span>
                    <span class="badge" ng-show="actor.stories_count">{{ actor.stories_count }}</span>
                </button>
            </div>
        </div>
    </div>

    <div class="panel-body">
        <form ng-submit="update(editableActor)"
              name='formHolder.actorForm'
              class="form-editable"
              ng-mouseleave="formHover(false)"
              ng-mouseover="formHover(true)"
              ng-class="{'form-editing': getShowActorForm(actor)}"
              show-validation
              novalidate>
            <div class="clearfix no-padding">
                <div class="form-half">
                    <label for="name">${message(code:'is.actor.name')}</label>
                    <input required
                           ng-focus="setEditableMode(true)"
                           ng-disabled="!getShowActorForm(actor)"
                           ng-maxlength="100"
                           name="name"
                           ng-model="editableActor.name"
                           type="text"
                           class="form-control">
                </div>
                <div class="form-half">
                    <label for="instances">${message(code:'is.actor.instances')}</label>
                    <select class="form-control"
                            ng-focus="setEditableMode(true)"
                            ng-disabled="!getShowActorForm(actor)"
                            name="instances"
                            ng-model="editableActor.instances"
                            ui-select2>
                        <is:options values="${BundleUtils.actorInstances}" />
                </select>
                </div>
            </div>
            <div class="clearfix no-padding">
                <div class="form-half">
                    <label for="expertnessLevel">${message(code:'is.actor.it.level')}</label>
                    <select class="form-control"
                            ng-focus="setEditableMode(true)"
                            ng-disabled="!getShowActorForm(actor)"
                            name="expertnessLevel"
                            ng-model="editableActor.expertnessLevel"
                            ui-select2>
                        <is:options values="${is.internationalizeValues(map: BundleUtils.actorLevels)}" />
                    </select>
                </div>
                <div class="form-half">
                    <label for="useFrequency">${message(code:'is.actor.use.frequency')}</label>
                    <select class="form-control"
                            ng-focus="setEditableMode(true)"
                            ng-disabled="!getShowActorForm(actor)"
                            name="useFrequency"
                            ng-model="editableActor.useFrequency"
                            ui-select2>
                        <is:options values="${is.internationalizeValues(map: BundleUtils.actorFrequencies)}" />
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="description">${message(code:'is.backlogelement.description')}</label>
                <textarea class="form-control"
                          ng-maxlength="3000"
                          ng-focus="setEditableMode(true)"
                          ng-disabled="!getShowActorForm(actor)"
                          placeholder="${message(code:'is.ui.backlogelement.nodescription')}"
                          name="description"
                          ng-model="editableActor.description"></textarea>
            </div>
            <div class="form-group">
                <label for="tags">${message(code:'is.backlogelement.tags')}</label>
                <input type="hidden"
                       ng-focus="setEditableMode(true)"
                       ng-disabled="!getShowActorForm(actor)"
                       class="form-control"
                       value="{{ editableActor.tags.join(',') }}"
                       name="tags"
                       ng-model="editableActor.tags"
                       data-placeholder="${message(code:'is.ui.backlogelement.notags')}"
                       ui-select2="selectTagsOptions"/>
            </div>
            <div class="form-group">
                <label for="notes">${message(code:'is.backlogelement.notes')}</label>
                <textarea is-markitup
                          class="form-control"
                          ng-maxlength="5000"
                          name="notes"
                          ng-model="editableActor.notes"
                          is-model-html="editableActor.notes_html"
                          ng-show="showNotesTextarea"
                          ng-blur="showNotesTextarea = false"
                          placeholder="${message(code: 'is.ui.backlogelement.nonotes')}"></textarea>
                <div class="markitup-preview"
                     ng-disabled="!getShowActorForm(actor)"
                     ng-show="!showNotesTextarea"
                     ng-click="showNotesTextarea = getShowActorForm(actor)"
                     ng-focus="setEditableMode(true); showNotesTextarea = getShowActorForm(actor)"
                     ng-class="{'placeholder': !editableActor.notes_html}"
                     tabindex="0"
                     ng-bind-html="(editableActor.notes_html ? editableActor.notes_html : '<p>${message(code: 'is.ui.backlogelement.nonotes')}</p>') | sanitize"></div>
            </div>
            <div class="btn-toolbar" ng-if="getShowActorForm(editableActor)">
                <button class="btn btn-primary pull-right"
                        ng-class="{ disabled: !isDirty() }"
                        tooltip="${message(code:'todo.is.ui.update')} (RETURN)"
                        tooltip-append-to-body="true"
                        type="submit">
                    ${message(code:'todo.is.ui.update')}
                </button>
                <button class="btn confirmation btn-default pull-right"
                        tooltip-append-to-body="true"
                        tooltip="${message(code:'is.button.cancel')}"
                        type="button"
                        ng-click="disableEditableActorMode()">
                    ${message(code:'is.button.cancel')}
                </button>
            </div>
        </form>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <tabset type="{{ tabsType }}">
            <tab select="$state.params.tabId ? setTabSelected('attachments') : ''"
                 heading="${message(code: 'is.ui.backlogelement.attachment')}"
                 active="tabSelected.attachments">
            </tab>
            <tab select="stories(actor); setTabSelected('stories');"
                 heading="${message(code: 'todo.is.actor.stories')}"
                 active="tabSelected.stories">
                <table class="table">
                    <tbody ng-include="'nested.stories.html'" ng-init="selected = actor"></tbody>
                </table>
            </tab>
        </tabset>
    </div>
</div>
</script>
