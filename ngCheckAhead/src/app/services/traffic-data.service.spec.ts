import { TestBed } from '@angular/core/testing';

import { TrafficDataService } from './traffic-data.service';

describe('TrafficDataService', () => {
  let service: TrafficDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrafficDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
