/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package at.bitfire.dav4jvm.property

import at.bitfire.dav4jvm.DavResource
import at.bitfire.dav4jvm.Property
import at.bitfire.dav4jvm.PropertyFactory
import at.bitfire.dav4jvm.XmlUtils
import nl.adaptivity.xmlutil.QName
import nl.adaptivity.xmlutil.XmlReader
import kotlin.jvm.JvmField

// see RFC 5397: WebDAV Current Principal Extension

data class CurrentUserPrincipal(
    val href: String?
) : Property {

    companion object {
        @JvmField
        val NAME = QName(XmlUtils.NS_WEBDAV, "current-user-principal")
    }


    object Factory : PropertyFactory {

        override fun getName() = NAME

        override fun create(parser: XmlReader): CurrentUserPrincipal {
            // <!ELEMENT current-user-principal (unauthenticated | href)>
            var href: String? = null
            XmlUtils.processTag(parser, DavResource.HREF) {
                href = XmlUtils.readText(parser)
            }
            return CurrentUserPrincipal(href)
        }

    }

}
