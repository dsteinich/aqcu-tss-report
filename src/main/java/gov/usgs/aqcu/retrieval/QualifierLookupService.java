package gov.usgs.aqcu.retrieval;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aquaticinformatics.aquarius.sdk.timeseries.servicemodels.Publish.QualifierListServiceResponse;
import com.aquaticinformatics.aquarius.sdk.timeseries.servicemodels.Publish.QualifierListServiceRequest;
import com.aquaticinformatics.aquarius.sdk.timeseries.servicemodels.Publish.Qualifier;
import com.aquaticinformatics.aquarius.sdk.timeseries.servicemodels.Publish.QualifierMetadata;
import gov.usgs.aqcu.exception.AquariusException;

@Component
public class QualifierLookupService extends AquariusRetrievalService {
	private static final Logger LOG = LoggerFactory.getLogger(QualifierLookupService.class);

	public List<QualifierMetadata> get() throws AquariusException {
		QualifierListServiceRequest request = new QualifierListServiceRequest();
		QualifierListServiceResponse qualifierListResponse = executePublishApiRequest(request);
		return qualifierListResponse.getQualifiers();
	}

	public List<QualifierMetadata> getByIdentifierList(List<String> includeIdentifiers) throws AquariusException {
		List<QualifierMetadata> filtered = new ArrayList<>();
		List<QualifierMetadata> metadataList = get();
		for(QualifierMetadata metadata : metadataList) {
			if(includeIdentifiers.contains(metadata.getIdentifier())) {
				filtered.add(metadata);
			}
		}
		
		return filtered;
	}

	public List<QualifierMetadata> getByQualifierList(List<Qualifier> includeQualifiers) throws AquariusException {
		List<String> qualifierIdentifiers = new ArrayList<>();

		for(Qualifier qual : includeQualifiers) {
			qualifierIdentifiers.add(qual.getIdentifier());
		}

		return getByIdentifierList(qualifierIdentifiers);
	}
}
