/**
 * Copyright 2009 Sami Dalouche
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sirika.pymager.api;

import org.springframework.core.io.InputStreamSource;

/**
 * Represents the Image Server, and the operations we can call on it. This is
 * the starting point of Image Server's client.
 * 
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 * 
 */
public interface ImageServer extends UrlGenerator {
    InputStreamSource downloadImage(ImageReference imageReference)
            throws ResourceNotExistingException,
            UnknownDownloadFailureException;

    ImageReference uploadImage(ImageId id, ImageFormat imageFormat,
            InputStreamSource imageSource) throws UnknownUploadFailureException;

    void deleteImage(ImageId imageId) throws UnknownDeleteFailureException;

    void destroy() throws Exception;
}
