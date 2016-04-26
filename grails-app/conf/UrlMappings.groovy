/*
 * Copyright (c) 2014 Kagilum.
 *
 * This file is part of iceScrum.
 *
 * iceScrum is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * iceScrum is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iceScrum.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Authors:
 *
 * Vincent Barrier (vbarrier@kagilum.com)
 */


import org.hibernate.ObjectNotFoundException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.acls.model.NotFoundException
import org.springframework.transaction.CannotCreateTransactionException
import com.mysql.jdbc.CommunicationsException

class UrlMappings {
    static mappings = {

        name default: "/$controller/$action/$id?" {
        }

        "/$action" {
            controller = 'scrumOS'
        }

        name privateURL: "/ws/$controller/$action/$id?" {
        }

        //New url mapping
        "/$product-F$uid/"{
            controller = 'feature'
            action = 'permalink'
            constraints {
                product(matches: /[0-9A-Z]*/)
                uid(matches: /[0-9]*/)
            }
        }
        "/$product-T$uid/"{
            controller = 'task'
            action = 'permalink'
            constraints {
                product(matches: /[0-9A-Z]*/)
                uid(matches: /[0-9]*/)
            }
        }
        "/$product-$uid/" {
            controller = 'story'
            action = 'permalink'
            constraints {
                product(matches: /[0-9A-Z]*/)
                uid(matches: /[0-9]*/)
            }
        }

        "/" {
            controller = 'scrumOS'
            action = 'index'
        }

        "/ui/window/$windowId" {
            controller = 'scrumOS'
            action = 'window'
            constraints {
                windowId(matches: /[a-zA-Z]*/)
            }
        }

        "/ui/widget/$widgetId" {
            controller = 'scrumOS'
            action = 'widget'
            constraints {
                widgetId(matches: /[a-zA-Z]*/)
            }
        }

        "/progress" {
            controller = 'scrumOS'
            action = 'progress'
        }

        "/login"(controller: 'login', action: 'auth')

        "/user" {
            controller = 'user'
            action = [GET: "index", POST:"save"]
        }

        "/user/retrieve" {
            controller = 'user'
            action = [GET: "retrieve", POST:"retrieve"]
        }

        "/user/menus" {
            controller = 'user'
            action = 'menus'
        }

        "/user/widgets" {
            controller = 'user'
            action = 'widgets'
        }

        "/user/$id" {
            controller = 'user'
            action = [GET: "show", PUT:"update", POST:"update"]
            constraints {
                id(matches: /\d*/)
            }
        }

        "/user/$id/activities" {
            controller = 'user'
            action = 'activities'
            constraints {
                id(matches: /\d*/)
            }
        }

        "/user/$id/widget" {
            controller = 'user'
            action = [POST: "widget"]
        }

        "/user/$id/menu" {
            controller = 'user'
            action = [POST: "menu"]
        }

        "/user/$id/unreadActivitiesCount" {
            controller = 'user'
            action = 'unreadActivitiesCount'
            constraints {
                id(matches: /\d*/)
            }
        }

        "/user/$id/avatar" {
            controller = 'user'
            action = 'avatar'
            constraints {
                id(matches: /\d*/)
            }
        }

        "/user/current" {
            controller = 'user'
            action = [GET: "current"]
        }

        "/user/available/$property" {
            controller = 'user'
            action = [POST: "available"]
            constraints {
                property(inList: ['username', 'email'])
            }
        }

        "/feed/$product" {
            controller = 'project'
            action = 'feed'
            constraints {
                product(matches: /[0-9A-Z]*/)
            }
        }

        "/project" {
            controller = 'project'
            action = [POST:"save"]
        }

        "/project/import" {
            controller = 'project'
            action = 'import'
        }


        "/project/importDialog" {
            controller = 'project'
            action = 'importDialog'
        }

        "/project/edit" {
            controller = 'project'
            action = 'edit'
        }

        "/project/$product/leaveTeam" {
            controller = 'project'
            action = 'leaveTeam'
            constraints {
                product(matches: /\d*/)
            }
        }

        "/project/$product/team" {
            controller = 'project'
            action = 'team'
            constraints {
                product(matches: /\d*/)
            }
        }

        "/project/$product/activities" {
            controller = 'project'
            action = 'activities'
            constraints {
                product(matches: /\d*/)
            }
        }

        "/project/$product/updateTeam" {
            controller = 'project'
            action = 'updateTeam'
        }

        "/project/$product/archive" {
            controller = 'project'
            action = 'archive'
        }

        "/project/$product/$action" {
            controller = 'project'
            constraints {
                action(inList: ['flowCumulative', 'velocityCapacity', 'velocity', 'parkingLot', 'burndown', 'burnup'])
            }
        }

        "/project/$product/backlogs" {
            controller = 'project'
            action = 'backlogs'
        }

        "/project/$product" {
            controller = 'project'
            action = [DELETE: "delete", POST: "update"]
            constraints {
                //must be the id
                product(matches: /\d*/)
            }
        }

        //case new project
        "/project/available/$property" {
            controller = 'project'
            action = [POST: "available"]
            constraints {
                property(inList: ['name', 'pkey'])
            }
        }

        //case update project
        "/project/$product/available/$property" {
            controller = 'project'
            action = [POST: "available"]
            constraints {
                product(matches: /\d*/)
                property(inList: ['name', 'pkey'])
            }
        }

        //Everything under project context
        "/p/$product/$controller/print" {
            action = 'print'
            constraints {
                product(matches: /[0-9A-Z]*/)
                controller(inList: ['backlog', 'actor', 'feature'])
            }
        }

        "/p/$product/export" {
            controller = 'project'
            action = 'export'
            constraints {
                product(matches: /[0-9A-Z]*/)
            }
        }

        "/p/$product/exportDialog" {
            controller = 'project'
            action = 'exportDialog'
            constraints {
                product(matches: /[0-9A-Z]*/)
            }
        }

        //handle flow js upload with pretty url
        "/p/$product/attachment/$type/$attachmentable/flow" {
            controller = 'attachment'
            action = [GET: "save", POST:"save"]
            constraints {
                product(matches: /[0-9A-Z]*/)
                attachmentable(matches: /\d*/)
                type(inList: ['story', 'task', 'actor', 'feature', 'release', 'sprint'])
            }
        }

        "/p/$product/attachment/$type/$attachmentable" {
            controller = 'attachment'
            action = [GET: "index", POST:"save"]
            constraints {
                product(matches: /[0-9A-Z]*/)
                attachmentable(matches: /\d*/)
                type(inList: ['story', 'task', 'actor', 'feature'])
            }
        }

        "/p/$product/attachment/$type/$attachmentable/$id" {
            controller = 'attachment'
            action = [GET: "show", DELETE:"delete"]
            constraints {
                product(matches: /[0-9A-Z]*/)
                attachmentable(matches: /\d*/)
                id(matches: /\d*/)
                type(inList: ['story', 'task', 'actor', 'feature'])
            }
        }

        "/team/" {
            controller = 'team'
            action = [GET: "index", POST: "save"]
        }

        "/team/$id" {
            controller = 'team'
            action = [POST:"update", DELETE:"delete"]
            constraints {
                id(matches: /\d*/)
            }
        }

        "/team/project/$product" {
            controller = 'team'
            action = 'show'
            constraints {
                product(matches: /\d*/)
            }
        }

        /* widgets url Mapping */

        "/widget/feed" {
            controller = 'feed'
            action = [GET:'list', POST:"save"]
        }
        "/widget/feed/mega" {
            controller = 'feed'
            action = [GET:'mega']
        }
        "/widget/feed/$id" {
            controller = 'feed'
            action = [DELETE:'delete']
            constraints {
                id(matches: /\d*/)
            }
        }
        "/widget/feed/$id/content" { // Not the REST resource which is returned, so not a REST action
            controller = 'feed'
            action = [GET:'content']
        }
        "/widget/feed/user" {
            controller = 'feed'
            action = [GET:"user"]
        }

        "404"(controller: "errors", action: "error404")
        "403"(controller: "errors", action: "error403")
        "400"(controller: "errors", action: "fakeError")
        "302"(controller: "errors", action: "fakeError")
        "500"(controller: "errors", action: "error404", exception: ObjectNotFoundException)
        "500"(controller: "errors", action: "error403", exception: AccessDeniedException)
        "500"(controller: "errors", action: "error403", exception: NotFoundException)
        "500"(controller: 'errors', action: 'memory', exception: OutOfMemoryError)
        "500"(controller: 'errors', action: 'database', exception: CannotCreateTransactionException)
        "500"(controller: 'errors', action: 'database', exception: CommunicationsException)
    }
}