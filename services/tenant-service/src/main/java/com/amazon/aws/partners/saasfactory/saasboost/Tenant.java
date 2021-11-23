/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazon.aws.partners.saasfactory.saasboost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"hasBilling"})
public class Tenant {

    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Boolean active = Boolean.FALSE;
    private String tier;
    private String onboardingStatus;
    private String name;
    private String subdomain;
    private String planId;
    private Map<String, Resource> resources = new HashMap<>();

    public Tenant() {
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public boolean isProvisioned() {
        return (onboardingStatus != null && !"created".equals(onboardingStatus) && !"failed".equals(onboardingStatus));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active != null ? active : Boolean.FALSE;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getOnboardingStatus() {
        return onboardingStatus;
    }

    public void setOnboardingStatus(String onboardingStatus) {
        this.onboardingStatus = onboardingStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Map<String, Resource> getResources() {
        return resources;
    }

    public void setResources(Map<String, Resource> resources) {
        this.resources = resources;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tenant other = (Tenant) obj;

        boolean resourcesEqual = resources != null && other.resources != null;
        if (resourcesEqual) {
            resourcesEqual = resources.size() == other.resources.size();
            if (resourcesEqual) {
                for (Map.Entry<String, Resource> resource : resources.entrySet()) {
                    resourcesEqual = resource.getValue().equals(other.resources.get(resource.getKey()));
                    if (!resourcesEqual) {
                        break;
                    }
                }
            }
        }
        return (
                ((id == null && other.id == null) || (id != null && id.equals(other.id)))
                && ((created == null && other.created == null) || (created != null && created.equals(other.created)))
                && ((modified == null && other.modified == null) || (modified != null && modified.equals(other.modified)))
                && ((active == null && other.active == null) || (active != null && active.equals(other.active)))
                && ((tier == null && other.tier == null) || (tier != null && tier.equals(other.tier)))
                && ((onboardingStatus == null && other.onboardingStatus == null) || (onboardingStatus != null && onboardingStatus.equals(other.onboardingStatus)))
                && ((name == null && other.name == null) || (name != null && name.equals(other.name)))
                && ((subdomain == null && other.subdomain == null) || (subdomain != null && subdomain.equals(other.subdomain)))
                && ((planId == null && other.planId == null) || (planId != null && planId.equals(other.planId)))
                && ((resources == null && other.resources == null) || resourcesEqual));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, modified, active, tier, onboardingStatus, name, subdomain, planId)
                + Arrays.hashCode(resources != null ? resources.keySet().toArray(new String[0]) : null)
                + Arrays.hashCode(resources != null ? resources.values().toArray(new Resource[0]) : null);
    }

    public static class Resource {

        String name;
        String arn;
        String consoleUrl;

        public Resource() {
            this(null, null, null);
        }

        public Resource(String name, String arn, String consoleUrl) {
            this.name = name;
            this.arn = arn;
            this.consoleUrl = consoleUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArn() {
            return arn;
        }

        public void setArn(String arn) {
            this.arn = arn;
        }

        public String getConsoleUrl() {
            return consoleUrl;
        }

        public void setConsoleUrl(String consoleUrl) {
            this.consoleUrl = consoleUrl;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Resource other = (Resource) obj;
            return (
                    ((name == null && other.name == null) || (name != null && name.equals(other.name)))
                    && ((arn == null && other.arn == null) || (arn != null && arn.equals(other.arn)))
                    && ((consoleUrl == null && other.consoleUrl == null) || (consoleUrl != null && consoleUrl.equals(other.consoleUrl))));
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, arn, consoleUrl);
        }
    }
}
