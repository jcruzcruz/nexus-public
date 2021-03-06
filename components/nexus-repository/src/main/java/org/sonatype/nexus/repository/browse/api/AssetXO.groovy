/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.repository.browse.api

import org.sonatype.nexus.repository.Repository
import org.sonatype.nexus.repository.browse.internal.api.RepositoryItemIDXO
import org.sonatype.nexus.repository.browse.internal.resources.AssetsResource
import org.sonatype.nexus.repository.storage.Asset

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.builder.Builder

import static org.sonatype.nexus.common.entity.EntityHelper.id

/**
 * Transfer object for use in the {@link AssetsResource}
 *
 * @since 3.3
 */
@CompileStatic
@Builder
@EqualsAndHashCode
class AssetXO
{
  String downloadUrl

  String coordinates

  String id

  static AssetXO fromAsset(final Asset asset, final Repository repository) {

    String internalId = id(asset).getValue();

    AssetXO assetXO = new AssetXO();
    assetXO.setCoordinates(asset.name());
    assetXO.setDownloadUrl(repository.getUrl() + "/" + asset.name());
    assetXO.setId(new RepositoryItemIDXO(repository.getName(), internalId).getValue());

    return assetXO;
  }
}
