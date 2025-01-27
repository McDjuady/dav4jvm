/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package at.bitfire.dav4jvm.property

import at.bitfire.dav4jvm.Dav4jvm
import at.bitfire.dav4jvm.Property
import at.bitfire.dav4jvm.PropertyFactory
import at.bitfire.dav4jvm.XmlUtils
import nl.adaptivity.xmlutil.QName
import nl.adaptivity.xmlutil.XmlReader
import kotlin.jvm.JvmField

data class MaxVCardSize(
    val maxSize: Long
) : Property {
    companion object {
        @JvmField
        val NAME = QName(XmlUtils.NS_CARDDAV, "max-resource-size")
    }

    object Factory : PropertyFactory {
        override fun getName() = NAME

        override fun create(parser: XmlReader): MaxVCardSize? {
            XmlUtils.readText(parser)?.let { valueStr ->
                try {
                    return MaxVCardSize(valueStr.toLong())
                } catch (e: NumberFormatException) {
                    Dav4jvm.log.warn("Couldn't parse $NAME: $valueStr", e)
                }
            }
            return null
        }
    }
}