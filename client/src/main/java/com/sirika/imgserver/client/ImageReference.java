package com.sirika.imgserver.client;

import static com.sirika.imgserver.client.ImageId.imageId;

import org.apache.commons.lang.Validate;
/**
 * Identifies the image that we want to retrieve.
 * <ul>
 *  <li>The Image ID</li>
 *  <li>The optional {@link ImageScale}</li>
 *  <li>The optional {@link ImageFormat} (defaults to {@link ImageFormat#JPEG}</li>
 * </ul>
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 *
 */
public class ImageReference {

    private ImageId id;
    private ImageScale rescaling;
    private ImageFormat imageFormat;
    
    private ImageReference(ImageId id) {
	this(id, null, null);
    }
    
    private ImageReference(ImageId id, ImageScale rescaling, ImageFormat imageFormat) {
	Validate.notNull(id);
	this.id = id;
	this.rescaling = rescaling;
	this.imageFormat = imageFormat;
    }
    
    public ImageReference getOriginalParentReference() {
	if(!isDerived()) {
	    return this;
	}
	return new ImageReference(this.id);
    }
    public static ImageReference originalImage(String id) {
	return new ImageReference(imageId(id));
    }
    
    public ImageReference rescaledTo(ImageScale imageScale) {
	return new ImageReference(this.id, imageScale, ImageFormat.JPEG);
    }
    
    public boolean isConverted() {
	return this.imageFormat != null;
    }
    
    public ImageReference convertedTo(ImageFormat imageFormat) {
	if(this.rescaling == null) {
	    throw new IllegalArgumentException("Because of a limitation of the image service, converting to another format also requires rescaling the image");
	}
	Validate.notNull(imageFormat);
	return new ImageReference(this.id, this.rescaling, imageFormat);
    }

    public String getId() {
        return id.toString();
    }

    public ImageScale getRescaling() {
        return rescaling;
    }
    
    public boolean isDerived() {
	return rescaling != null;
    }

    public ImageFormat getImageFormat() {
        return imageFormat;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result
		+ ((imageFormat == null) ? 0 : imageFormat.hashCode());
	result = prime * result
		+ ((rescaling == null) ? 0 : rescaling.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ImageReference other = (ImageReference) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (imageFormat == null) {
	    if (other.imageFormat != null)
		return false;
	} else if (!imageFormat.equals(other.imageFormat))
	    return false;
	if (rescaling == null) {
	    if (other.rescaling != null)
		return false;
	} else if (!rescaling.equals(other.rescaling))
	    return false;
	return true;
    }
}
