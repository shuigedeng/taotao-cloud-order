/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.order.domain.valobj.invoice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

import static java.time.Instant.now;
import static lombok.AccessLevel.PRIVATE;

/**
 * Invoice
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class Invoice {

    private InvoiceTitle title;
    private InvoiceType type;
    private String email;
    private Instant requestedAt;

    private List<UploadedFile> files;
    private Instant issuedAt;

    public Invoice( InvoiceTitle title, InvoiceType type, String email ) {
        this.type = type;
        this.title = title;
        this.email = email;
        this.requestedAt = now();
    }

    public void issue( List<UploadedFile> files ) {
        this.files = files;
        this.issuedAt = now();
    }

    public boolean isIssued() {
        return issuedAt != null;
    }
}
